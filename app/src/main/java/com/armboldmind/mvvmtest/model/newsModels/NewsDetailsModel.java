package com.armboldmind.mvvmtest.model.newsModels;

import com.armboldmind.mvvmtest.model.MediaFileModel;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDetailsModel {
    private long id;
    private ArrayList<MediaFileModel> imageUrlList;
    private String title;
    private String description;
    private String createdDate;
}