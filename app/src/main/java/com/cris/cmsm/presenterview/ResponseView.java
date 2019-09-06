package com.cris.cmsm.presenterview;

/**
 *
 */
public interface ResponseView extends IMainView {

    void ResponseOk(Object object, int position);

    void Error();
}
