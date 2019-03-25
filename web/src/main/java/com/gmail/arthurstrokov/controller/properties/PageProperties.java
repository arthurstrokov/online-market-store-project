package com.gmail.arthurstrokov.controller.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component("pageProperties")
public class PageProperties {

    private final Environment environment;

    private Long quantityForPage;

    private String registrationPagePath;
    private String loginPagePath;
    private String usersPagePath;
    private String itemsPagePath;
    private String userOrdersPagePath;
    private String userOrderedItemsPagePath;
    private String userOrdersChangeStatusPagePath;
    private String storePagePath;
    private String errorsPagePath;
    private String newsCreatePagePath;
    private String newsPagePath;
    private String newsCreateCommentsPagePath;
    private String newsReadCommentsPagePath;
    private String userRolePagePath;
    private String profilePagePath;
    private String profileUpdatePagePath;
    private String userPasswordPagePath;
    private String fileUploadPagePath;
    private String createItemPagePath;
    private String newsUpdatePagePath;
    private String buisnessCardPagePath;
    private String buisnessCardCreatePagePath;

    @Autowired
    public PageProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void initialize() {
        this.quantityForPage = Long.valueOf(Objects.requireNonNull(environment.getProperty("quantity.for.page")));
        this.registrationPagePath = environment.getProperty("registration.page.path");
        this.loginPagePath = environment.getProperty("login.page.path");
        this.usersPagePath = environment.getProperty("users.page.path");
        this.itemsPagePath = environment.getProperty("items.page.path");
        this.userOrderedItemsPagePath = environment.getProperty("user.ordered.items.page.path");
        this.userOrdersPagePath = environment.getProperty("user.orders.page.path");
        this.userOrdersChangeStatusPagePath = environment.getProperty("user.orders.change.status.page.path");
        this.userRolePagePath = environment.getProperty("user.role.page.path");
        this.storePagePath = environment.getProperty("store.page.path");
        this.errorsPagePath = environment.getProperty("errors.page.path");
        this.newsCreatePagePath = environment.getProperty("news.create.page.path");
        this.newsUpdatePagePath = environment.getProperty("news.update.page.path");
        this.newsPagePath = environment.getProperty("news.page.path");
        this.newsCreateCommentsPagePath = environment.getProperty("news.create.comments.page.path");
        this.newsReadCommentsPagePath = environment.getProperty("news.read.comments.page.path");
        this.userPasswordPagePath = environment.getProperty("user.password.page.path");
        this.fileUploadPagePath = environment.getProperty("file.upload.page.path");
        this.createItemPagePath = environment.getProperty("create.item.page.path");
        this.profilePagePath = environment.getProperty("profile.page.path");
        this.profileUpdatePagePath = environment.getProperty("profile.update.page.path");
        this.buisnessCardPagePath = environment.getProperty("buisness.card.page.path");
        this.buisnessCardCreatePagePath = environment.getProperty("buisness.card.create.page.path");
    }

    public Long getQuantityForPage() {
        return quantityForPage;
    }

    public String getRegistrationPagePath() {
        return registrationPagePath;
    }

    public String getLoginPagePath() {
        return loginPagePath;
    }

    public String getUsersPagePath() {
        return usersPagePath;
    }

    public String getItemsPagePath() {
        return itemsPagePath;
    }

    public String getUserOrdersPagePath() {
        return userOrdersPagePath;
    }

    public String getUserOrderedItemsPagePath() {
        return userOrderedItemsPagePath;
    }

    public String getUserOrdersChangeStatusPagePath() {
        return userOrdersChangeStatusPagePath;
    }

    public String getStorePagePath() {
        return storePagePath;
    }

    public String getErrorsPagePath() {
        return errorsPagePath;
    }

    public String getNewsCreatePagePath() {
        return newsCreatePagePath;
    }

    public String getNewsPagePath() {
        return newsPagePath;
    }

    public String getNewsCreateCommentsPagePath() {
        return newsCreateCommentsPagePath;
    }

    public String getNewsReadCommentsPagePath() {
        return newsReadCommentsPagePath;
    }

    public String getUserRolePagePath() {
        return userRolePagePath;
    }

    public String getProfilePagePath() {
        return profilePagePath;
    }

    public String getProfileUpdatePagePath() {
        return profileUpdatePagePath;
    }

    public String getUserPasswordPagePath() {
        return userPasswordPagePath;
    }

    public String getFileUploadPagePath() {
        return fileUploadPagePath;
    }

    public String getCreateItemPagePath() {
        return createItemPagePath;
    }

    public String getNewsUpdatePagePath() {
        return newsUpdatePagePath;
    }

    public String getBuisnessCardPagePath() {
        return buisnessCardPagePath;
    }

    public String getBuisnessCardCreatePagePath() {
        return buisnessCardCreatePagePath;
    }
}
