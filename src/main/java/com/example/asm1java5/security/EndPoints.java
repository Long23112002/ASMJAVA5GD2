package com.example.asm1java5.security;

public class EndPoints {

    public static final String[] GET_ADMIN_STAFF = {
            "/auth/login",
            "/bill/index",
            "/bill/edit/**",
            "/bill-detail/index",
            "/color/index",
            "/color/create",
            "/color/edit/**",
            "/color/delete/**",
            "/customer/index",
            "/customer/create",
            "/customer/delete/**",
            "/product/index",
            "/product/create",
            "/product/edit/**",
            "/product/delete/**",
            "/product-detail/index",
            "/product-detail/create",
            "/product-detail/edit/**",
            "/product-detail/delete/**",
            "/sell-manager/index",
            "/sell-manager/choose-customer/**",
            "/sell-manager/create-order",
            "/sell-manager/cancel-order/**",
            "/sell-manager/choose-oder/**",
            "/size/index",
            "/size/create",
            "/size/edit/**",
            "/size/delete/**",
            "/staff/index",
            "/staff/create",
            "/staff/edit/**",
            "/staff/delete/**",
    };

    public static final String[] POST_ADMIN_STAFF = {
            "/auth/login-action",
            "/bill/update/**",
            "/bill/filter",
            "/color/search",
            "/color/store",
            "/color/update/**",
            "/customer/search",
            "/customer/store",
            "/customer/update/**",
            "/product/search",
            "/product/store",
            "/product/update/**",
            "/product-detail/filter",
            "/product-detail/store",
            "/product-detail/update/**",
            "/sell-manager/filter",
            "/sell-manager/add-customer",
            "/size/search",
            "/size/store",
            "/size/update/**",
            "/staff/search",
            "/staff/store",
            "/staff/update/**",
    };



}
