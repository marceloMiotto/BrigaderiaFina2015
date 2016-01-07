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
        public static final String TABLE_NAME                     = "subgrupo_flavors";
        public static final String COLUMN_NAME_SUBGRUPO_NAME      = "subgrupo_name";
        public static final String COLUMN_NAME_SUBGRUPO_FLAVORS   = "flavors";
    }


    public static abstract class Events implements BaseColumns{
        public static final String TABLE_NAME                     = "events";
        public static final String COLUMN_NAME_EVENT_CODE         = "event_code";
        public static final String COLUMN_NAME_EVENT_NAME         = "event_name";
        public static final String COLUMN_NAME_EVENT_DATE         = "event_date";
        public static final String COLUMN_NAME_EVENT_TYPE         = "event_type";
        public static final String COLUMN_NAME_EVENT_DESCRIPTION  = "event_description";
    }
}
