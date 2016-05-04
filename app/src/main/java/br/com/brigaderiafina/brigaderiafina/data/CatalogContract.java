package br.com.brigaderiafina.brigaderiafina.data;


import android.provider.BaseColumns;

public final class CatalogContract {

    public CatalogContract(){}

    public static final String COLUMN_NAME_NULLABLE = null;

    public static abstract class CatalogSubgroup implements BaseColumns{
        public static final String TABLE_NAME                 = "subgroups";
        public static final String COLUMN_NAME_LINE_NAME      = "line";
        public static final String COLUMN_NAME_GROUP_NAME     = "group_name";
        public static final String COLUMN_NAME_SUBGRUPO_NAME  = "name";
        public static final String COLUMN_NAME_SUBGRUPO_PRICE = "price";
        public static final String COLUMN_NAME_SUBGRUPO_PHOTO = "photo";
    }

    public static abstract class CatalogSubgroupFlavors implements BaseColumns{
        public static final String TABLE_NAME                     = "subgroup_flavors";
        public static final String COLUMN_NAME_SUBGROUP_NAME      = "subgroup_name";
        public static final String COLUMN_NAME_SUBGROUP_FLAVORS   = "flavors";
    }

    public static abstract class CatalogSubgroupPhotos implements BaseColumns{
        public static final String TABLE_NAME                     = "subgroup_photos";
        public static final String COLUMN_NAME_LINE               = "subgroup_line";
        public static final String COLUMN_NAME_SUBGROUP_NAME      = "subgroup_name";
        public static final String COLUMN_NAME_PHOTOS_PATH        = "subgroup_photos_path";
        public static final String COLUMN_NAME_PHOTOS_NAME        = "subgroup_photos_name";
    }


    public static abstract class Events implements BaseColumns{
        public static final String TABLE_NAME                     = "events";
        public static final String COLUMN_NAME_EVENT_CODE         = "event_code";
        public static final String COLUMN_NAME_EVENT_NAME         = "event_name";
        public static final String COLUMN_NAME_EVENT_TYPE         = "event_type";
        public static final String COLUMN_NAME_EVENT_DESCRIPTION  = "event_description";
        public static final String COLUMN_NAME_EVENT_MAIN_PHOTO   = "event_main_photo";

    }

    public static abstract class EventsPhotos implements BaseColumns{
        public static final String TABLE_NAME                     = "events_photos";
        public static final String COLUMN_NAME_EVENT_CODE         = "event_code";
        public static final String COLUMN_NAME_PHOTOS_PATH        = "subgroup_photos_path";
        public static final String COLUMN_NAME_PHOTOS_NAME        = "subgroup_photos_name";
    }

}
