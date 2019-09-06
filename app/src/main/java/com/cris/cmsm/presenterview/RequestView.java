package com.cris.cmsm.presenterview;

/**
 *
 */
public interface RequestView extends IMainView {

    void Request(Object object, String msg, int position);

    void Error();
}
