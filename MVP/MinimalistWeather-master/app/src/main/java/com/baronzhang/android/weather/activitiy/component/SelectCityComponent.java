package com.baronzhang.android.weather.activitiy.component;

import com.baronzhang.android.weather.activitiy.SelectCityActivity;
import com.baronzhang.android.weather.activitiy.module.SelectCityModule;
import com.baronzhang.android.weather.util.ActivityScoped;

import dagger.Component;
import com.baronzhang.android.weather.ApplicationComponent;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com ==>> baronzhang.com)
 *         2016/11/30
 */
@ActivityScoped
@Component(modules = SelectCityModule.class, dependencies = ApplicationComponent.class)
public interface SelectCityComponent {

    void inject(SelectCityActivity selectCityActivity);
}
