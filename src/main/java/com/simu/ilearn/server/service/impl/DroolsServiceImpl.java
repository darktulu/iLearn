package com.simu.ilearn.server.service.impl;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;

public class DroolsServiceImpl {
    private KnowledgeBase kbase;
    private StatelessKnowledgeSession ksession;

    public KnowledgeBase getKbase() {
        return kbase;
    }

    public void setKbase(KnowledgeBase kbase) {
        this.kbase = kbase;
    }

    public StatelessKnowledgeSession getKsession() {
        return ksession;
    }

    public void setKsession(StatelessKnowledgeSession ksession) {
        this.ksession = ksession;
    }
}
