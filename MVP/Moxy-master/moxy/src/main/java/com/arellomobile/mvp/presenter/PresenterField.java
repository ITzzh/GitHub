package com.arellomobile.mvp.presenter;

import com.arellomobile.mvp.*;

/**
 * Date: 18-Dec-15
 * Time: 17:50
 *
 * @author Alexander Blinov
 * @author Yuri Shmakov
 */
public abstract class PresenterField<Presenter extends MvpPresenter<? extends View>, View extends MvpView> {
	protected final String tag;
	protected final PresenterType presenterType;
	protected final String presenterId;
	protected final Class<? extends MvpPresenter<?>> presenterClass;

	protected PresenterField(String tag, PresenterType presenterType, String presenterId, Class<? extends MvpPresenter<?>> presenterClass) {
		this.tag = tag;
		this.presenterType = presenterType;
		this.presenterId = presenterId;
		this.presenterClass = presenterClass;
	}

	public abstract void bind(Object target, MvpPresenter presenter);

	public String getTag(Object delegated) {
		return tag != null ? tag : getClass().getSimpleName();
	}

	public PresenterType getPresenterType() {
		return presenterType;
	}

	public String getPresenterId() {
		return presenterId;
	}

	public Class<? extends MvpPresenter<?>> getPresenterClass() {
		return presenterClass;
	}

	public abstract MvpPresenter<?> providePresenter(Object delegated);
}
