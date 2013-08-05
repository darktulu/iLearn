package com.simu.ilearn.common.client.widget;

import com.github.gwtbootstrap.client.ui.ProgressBar;
import com.google.common.base.Strings;
import com.google.gwt.event.dom.client.DragEnterEvent;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xhr2.client.FileRequestCallback;
import com.google.gwt.xhr2.client.FormData;
import com.google.gwt.xhr2.client.LoadStartHandler;
import com.google.gwt.xhr2.client.ProgressEvent;
import com.google.gwt.xhr2.client.ProgressHandler;
import com.google.gwt.xhr2.client.Request;
import com.google.gwt.xhr2.client.RequestBuilder;
import com.google.gwt.xhr2.client.Response;
import com.google.inject.assistedinject.Assisted;
import org.vectomatic.dnd.DataTransferExt;
import org.vectomatic.dnd.DropPanel;
import org.vectomatic.file.File;
import org.vectomatic.file.FileList;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.FileUploadExt;
import org.vectomatic.file.events.LoadEndEvent;
import org.vectomatic.file.events.LoadEndHandler;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DnDUpload extends Composite {
    public interface Binder extends UiBinder<Widget, DnDUpload> {
    }

    private static final Logger logger = Logger.getLogger(DnDUpload.class.getName());

    private final String GRAY = "#DDD";
    private final String BLUE = "#437DD4";

    @UiField
    HTMLPanel dropWrapper;
    @UiField
    DropPanel dropZone;
    @UiField
    FileUploadExt fileUpload;
    @UiField
    Label messageLabel;
    @UiField
    HTMLPanel uploadingContainer;
    @UiField
    ProgressBar progressBar;

    private final FileReader fileReader;
    private final FileType fileType;

    private File currentFile;
    private RequestBuilder requestBuilder;
    private FileRequestCallback fileRequestCallback;

    @Inject
    DnDUpload(Binder uiBinder,
              @Assisted FileType fileType,
              @Assisted String servletPath) {
        this.fileReader = new FileReader();
        this.fileType = fileType;
        this.fileReader.addLoadEndHandler(new LoadEndHandler() {
            @Override
            public void onLoadEnd(LoadEndEvent loadEndEvent) {
                logger.log(Level.INFO, "File successfully loaded..");
                if (fileReader.getError() == null) {
                    if (currentFile != null) {
                        sendRequest();
                    }
                }
            }
        });

        initWidget(uiBinder.createAndBindUi(this));
        setUpRequestBuilder(servletPath);
    }

    public void setFileRequestCallback(FileRequestCallback fileRequestCallback) {
        this.fileRequestCallback = fileRequestCallback;
    }

    @UiHandler("dropZone")
    void onDragOver(DragOverEvent event) {
        event.stopPropagation();
        event.preventDefault();
    }

    @UiHandler("dropZone")
    void onDragEnter(DragEnterEvent event) {
        dropWrapper.getElement().getStyle().setBorderColor(BLUE);
        messageLabel.getElement().getStyle().setColor(BLUE);
        event.stopPropagation();
        event.preventDefault();
    }

    @UiHandler("dropZone")
    void onDragLeave(DragLeaveEvent event) {
        dropWrapper.getElement().getStyle().setBorderColor(GRAY);
        messageLabel.getElement().getStyle().setColor(GRAY);
        event.stopPropagation();
        event.preventDefault();
    }

    @UiHandler("dropZone")
    void onDrop(DropEvent event) {
        processFiles(event.getDataTransfer().<DataTransferExt>cast().getFiles());
        dropWrapper.getElement().getStyle().setBorderColor(GRAY);
        messageLabel.getElement().getStyle().setColor(GRAY);
        event.stopPropagation();
        event.preventDefault();
    }

    private void processFiles(FileList files) {
        logger.log(Level.INFO, "Files to be uploaded : " + files.getLength());
        if (files.getLength() >= 1) {
            currentFile = files.getItem(0);
            String type = currentFile.getType();

            if (type.startsWith(fileType.getMimeType())) {
                switch (fileType) {
                    case IMAGE:
                        fileReader.readAsBinaryString(currentFile);
                        break;
                    case TEXT:
                        fileReader.readAsText(currentFile);
                        break;
                    case ZIP:
                        fileReader.readAsBinaryString(currentFile);
                        break;
                }
            } else {
                Window.alert("Type de fichier non supporté.");
            }
        }
    }

    private void setUpRequestBuilder(String uploadUrl) {
        requestBuilder = new RequestBuilder(uploadUrl, "POST");
        requestBuilder.setUploadLoadStartHandler(new LoadStartHandler() {
            @Override
            public void onLoadStart(ProgressEvent event) {
                logger.log(Level.INFO, "Starting upload : " + currentFile.getName());
                dropZone.setVisible(false);
                uploadingContainer.setVisible(true);
            }
        });

        requestBuilder.setUploadProgressHandler(new ProgressHandler() {
            @Override
            public void onProgress(ProgressEvent event) {
                if (currentFile != null) {
                    logger.log(Level.INFO, "Uploading : " + (event.getLoaded() / currentFile.getSize()));
                    progressBar.setPercent((int) ((event.getLoaded() / currentFile.getSize()) * 100));
                }
            }
        });

        requestBuilder.setCallback(new FileRequestCallback() {
            @Override
            public void onResponseReceived(Request request, Response resp) {
                logger.log(Level.INFO, "The File was uploaded to the Server...");
                currentFile = null;
                dropZone.setVisible(true);
                uploadingContainer.setVisible(false);

                if (Strings.isNullOrEmpty(resp.getResponseText())) {
                    fileRequestCallback.onError(request, new Exception());
                } else {
                    if ("success".equals(resp.getResponseText())) {
                        fileRequestCallback.onResponseReceived(request, resp);
                    } else {
                        fileRequestCallback.onError(request, new Exception());
                    }
                }
            }
        });
    }

    private void sendRequest() {
        FormData formData = FormData.create();
        formData.append("file", currentFile);
        requestBuilder.setRequestFormData(formData);
        try {
            requestBuilder.send();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }
}
