package com.simu.ilearn.app.client.web.application.learn.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.simu.ilearn.common.shared.vo.LearnVO;

public class LearnChangedEvent extends GwtEvent<LearnChangedEvent.LearnChangedHandler> {
    public interface LearnChangedHandler extends EventHandler {
        void onLearnChanged(LearnChangedEvent event);
    }

    private static final Type<LearnChangedHandler> TYPE = new Type<LearnChangedHandler>();

    private LearnVO learn;

    public LearnChangedEvent() {
    }

    public LearnChangedEvent(LearnVO learn) {
        this.learn = learn;
    }

    public static Type<LearnChangedHandler> getType() {
        return TYPE;
    }

    public LearnVO getLearn() {
        return learn;
    }

    public static void fire(HasHandlers source, LearnVO learn) {
        source.fireEvent(new LearnChangedEvent(learn));
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new LearnChangedEvent());
    }

    @Override
    public Type<LearnChangedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LearnChangedHandler handler) {
        handler.onLearnChanged(this);
    }
}
