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

package com.simu.ilearn.common.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

public class ReloadEvent extends GwtEvent<ReloadEvent.ReloadEventHandler> {
    public interface ReloadEventHandler extends EventHandler {
        void onRequestEvent(ReloadEvent requestEvent);
    }

    private static final Type<ReloadEventHandler> TYPE = new Type<ReloadEventHandler>();

    private final PlaceRequest placeRequest;

    public ReloadEvent(PlaceRequest placeRequest) {
        this.placeRequest = placeRequest;
    }

    public static Type<ReloadEventHandler> getType() {
        return TYPE;
    }

    public PlaceRequest getPlaceRequest() {
        return placeRequest;
    }

    @Override
    public Type<ReloadEventHandler> getAssociatedType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, PlaceRequest placeRequest) {
        source.fireEvent(new ReloadEvent(placeRequest));
    }

    @Override
    protected void dispatch(ReloadEventHandler reloadEventHandler) {
        reloadEventHandler.onRequestEvent(this);
    }
}
