/**
 * Copyright 2012 Leyton Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.simu.ilearn.server.service;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InspirationTest {
    protected static DesiredCapabilities sCaps;
    private String DRIVER = "http://localhost:8089";
    private String PHANTOMJS_EXEC_PATH = "/home/simu/programing/phantomjs-1.9.2-linux-x86_64/bin/phantomjs";
    private RemoteWebDriver mDriver;

    @Test
    public void nothing() throws IOException, InterruptedException {
        initPhantomJS();

        String url = "http://planning.coopdefrance.coop/wd140awp/wd140awp.exe/connect/FichierCoop";

        mDriver.get(url);
        render();

        for (int p = 0; p < 108; p++) {
            List<ObjectMap> pageResult = Lists.newArrayList();
            for (int i = 1; i < 22; i++) {
                System.out.println(" =================  PAGE : " + p + ", ITEM : " + i + " ================");

                mDriver.findElementByCssSelector("#A10_" + i + " a").click();
                render();

                List<WebElement> elements = mDriver.findElementsByCssSelector("input");
                ObjectMap row = new ObjectMap();
                row.setNomUsuel(elements.get(2).getAttribute("value").trim());
                row.setDirecteur(elements.get(3).getAttribute("value").trim());
                row.setPresident(elements.get(4).getAttribute("value").trim());
                row.setAdresse(elements.get(5).getAttribute("value").trim());
                row.setNaf(elements.get(6).getAttribute("value").trim());
                row.setDepartement(elements.get(7).getAttribute("value").trim());
                row.setRegion(elements.get(8).getAttribute("value").trim());
                row.setRaisonSociale(elements.get(9).getAttribute("value").trim());
                row.setSiret(elements.get(10).getAttribute("value").trim());
                row.setDepartementNumero(elements.get(11).getAttribute("value").trim());
                row.setTelephone(elements.get(12).getAttribute("value").trim());
                row.setFax(elements.get(13).getAttribute("value").trim());
                row.setEmail(elements.get(14).getAttribute("value").trim());
                row.setSiteWeb(elements.get(15).getAttribute("value").trim());
                row.setBranche(elements.get(16).getAttribute("value").trim());
                row.setChiffreAffaires(elements.get(17).getAttribute("value").trim());
                row.setChiffreAffairesGroupe(elements.get(18).getAttribute("value").trim());
                row.setEffectifs(elements.get(19).getAttribute("value").trim());
                row.setEffectifsGroupe(elements.get(20).getAttribute("value").trim());
                row.setAgrement(elements.get(21).getAttribute("value").trim());
                row.setAdherents(elements.get(22).getAttribute("value").trim());

                pageResult.add(row);
                mDriver.navigate().back();
            }

            writeToFile(pageResult);

            mDriver.findElementByCssSelector("#A23 .suivant a").click();
            render();
        }
    }

    private void initPhantomJS() throws MalformedURLException {
        // Prepare capabilities
        sCaps = new DesiredCapabilities();
        sCaps.setJavascriptEnabled(true);
        sCaps.setCapability("takesScreenshot", false);

        // Fetch PhantomJS-specific configuration parameters
        // "PHANTOMJS_EXEC_PATH"
        sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOMJS_EXEC_PATH);

        // Disable "web-security", enable all possible "ssl-protocols" and "ignore-ssl-errors" for PhantomJSDriver
        ArrayList<String> cliArgsCap = new ArrayList<String>();
        cliArgsCap.add("--web-security=false");
        cliArgsCap.add("--ssl-protocol=any");
        cliArgsCap.add("--ignore-ssl-errors=true");
        cliArgsCap.add("--load-images=no");
        sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);

        sCaps.setBrowserName("phantomjs");
        mDriver = new RemoteWebDriver(new URL(DRIVER), sCaps);
    }

    private void render() throws InterruptedException {
        String firstResult = "";
        String result = "";
        do {
            firstResult = mDriver.getPageSource();
            Thread.sleep(100);
            result = mDriver.getPageSource();
        } while (!firstResult.equals(result));
    }

    private String render(URL url, javax.servlet.http.Cookie[] cookies) throws InterruptedException {
        mDriver.get(url.toString());
        mDriver.manage().deleteAllCookies();
        for (javax.servlet.http.Cookie cookie : cookies) {
            mDriver.manage().addCookie(new Cookie(cookie.getName(), cookie.getValue()));
        }
        String firstResult = "";
        String result = "";
        do {
            firstResult = mDriver.getPageSource();
            Thread.sleep(1000);
            result = mDriver.getPageSource();
        } while (!firstResult.equals(result));

        return result;
    }

    private void writeToFile(List<ObjectMap> list) throws IOException {
        CsvBeanWriter beanWriter = null;
        try {
            beanWriter = new CsvBeanWriter(new FileWriter("target/menar_result_20131118.csv", true),
                    CsvPreference.EXCEL_PREFERENCE);

            // the header elements are used to map the bean values to each column (names must match)
            final String[] header = new String[]{"Raison Sociale", "Nom Usuel / Sigle", "Siret", "Naf","Agrément", "Département", "Département Numéro", "Région", "Adresse", "Téléphone", "Fax","eMail", "Site Web", "Président", "Directeur", "Branche", "Adhérents","Chiffre d'affaires", "Chiffre d'affaires groupe", "Effectifs", "Effectifs groupe"};
            final CellProcessor[] processors = new CellProcessor[]{
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional()
            };

            String[] mapping = new String[]{"RaisonSociale",
                    "nomUsuel",
                    "siret",
                    "naf",
                    "agrement",
                    "departement",
                    "departementNumero",
                    "region",
                    "adresse",
                    "telephone",
                    "fax",
                    "email",
                    "siteWeb",
                    "president",
                    "directeur",
                    "branche",
                    "adherents",
                    "chiffreAffaires",
                    "chiffreAffairesGroupe",
                    "effectifs",
                    "effectifsGroupe"
            };

            // write the header
            //beanWriter.writeHeader(header);

            // write the beans
            for (ObjectMap customer : list) {
                beanWriter.write(customer, mapping, processors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (beanWriter != null) {
                beanWriter.close();
            }
        }
    }

    public class ObjectMap {
        private String raisonSociale;
        private String nomUsuel;
        private String siret;
        private String naf;
        private String agrement;
        private String departement;
        private String departementNumero;
        private String region;
        private String adresse;
        private String telephone;
        private String fax;
        private String email;
        private String siteWeb;
        private String president;
        private String directeur;
        private String branche;
        private String adherents;
        private String chiffreAffaires;
        private String chiffreAffairesGroupe;
        private String effectifs;
        private String effectifsGroupe;

        public String getRaisonSociale() {
            return raisonSociale;
        }

        public void setRaisonSociale(String raisonSociale) {
            this.raisonSociale = raisonSociale;
        }

        public String getNomUsuel() {
            return nomUsuel;
        }

        public void setNomUsuel(String nomUsuel) {
            this.nomUsuel = nomUsuel;
        }

        public String getSiret() {
            return siret;
        }

        public void setSiret(String siret) {
            this.siret = siret;
        }

        public String getNaf() {
            return naf;
        }

        public void setNaf(String naf) {
            this.naf = naf;
        }

        public String getAgrement() {
            return agrement;
        }

        public void setAgrement(String agrement) {
            this.agrement = agrement;
        }

        public String getDepartement() {
            return departement;
        }

        public void setDepartement(String departement) {
            this.departement = departement;
        }

        public String getDepartementNumero() {
            return departementNumero;
        }

        public void setDepartementNumero(String departementNumero) {
            this.departementNumero = departementNumero;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getAdresse() {
            return adresse;
        }

        public void setAdresse(String adresse) {
            this.adresse = adresse;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSiteWeb() {
            return siteWeb;
        }

        public void setSiteWeb(String siteWeb) {
            this.siteWeb = siteWeb;
        }

        public String getPresident() {
            return president;
        }

        public void setPresident(String president) {
            this.president = president;
        }

        public String getDirecteur() {
            return directeur;
        }

        public void setDirecteur(String directeur) {
            this.directeur = directeur;
        }

        public String getBranche() {
            return branche;
        }

        public void setBranche(String branche) {
            this.branche = branche;
        }

        public String getAdherents() {
            return adherents;
        }

        public void setAdherents(String adherents) {
            this.adherents = adherents;
        }

        public String getChiffreAffaires() {
            return chiffreAffaires;
        }

        public void setChiffreAffaires(String chiffreAffaires) {
            this.chiffreAffaires = chiffreAffaires;
        }

        public String getChiffreAffairesGroupe() {
            return chiffreAffairesGroupe;
        }

        public void setChiffreAffairesGroupe(String chiffreAffairesGroupe) {
            this.chiffreAffairesGroupe = chiffreAffairesGroupe;
        }

        public String getEffectifs() {
            return effectifs;
        }

        public void setEffectifs(String effectifs) {
            this.effectifs = effectifs;
        }

        public String getEffectifsGroupe() {
            return effectifsGroupe;
        }

        public void setEffectifsGroupe(String effectifsGroupe) {
            this.effectifsGroupe = effectifsGroupe;
        }
    }
}
