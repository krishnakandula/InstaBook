package com.canvas.instabook.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Krishna Chaitanya Kandula on 7/4/17.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScoped {
}
