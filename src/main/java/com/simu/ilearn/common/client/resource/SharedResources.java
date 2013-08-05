package com.simu.ilearn.common.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.simu.ilearn.common.client.resource.style.ButtonStyle;
import com.simu.ilearn.common.client.resource.style.NavListStyle;
import com.simu.ilearn.common.client.resource.style.PopupStyle;
import com.simu.ilearn.common.client.resource.style.SearchStyle;
import com.simu.ilearn.common.client.resource.style.SharedStyle;

public interface SharedResources extends ClientBundle {
    @Source("com/simu/ilearn/common/client/resource/style/sharedStyle.css")
    SharedStyle sharedStyleCss();

    @Source("com/simu/ilearn/common/client/resource/style/popupStyle.css")
    PopupStyle popupStyleCss();

    @Source("com/simu/ilearn/common/client/resource/style/navListStyle.css")
    NavListStyle navListStyleCss();

    @Source("com/simu/ilearn/common/client/resource/style/buttonStyle.css")
    ButtonStyle buttonStyleCss();

    @Source("com/simu/ilearn/common/client/resource/style/searchStyle.css")
    SearchStyle searchStyleCss();

    @Source("com/simu/ilearn/common/client/resource/image/required.png")
    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
    ImageResource required();

    @Source("com/simu/ilearn/common/client/resource/image/serverDown.png")
    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
    ImageResource serverDown();

    @Source("com/simu/ilearn/common/client/resource/image/arrow-down.png")
    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
    ImageResource arrowDown();

    @Source("com/simu/ilearn/common/client/resource/image/arrow-down-hover.png")
    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
    ImageResource arrowDownHover();
}
