package br.com.brigaderiafina.brigaderiafina.data;

public class CatalogSQLHelper {

    private static final String TEXT_TYPE  = " TEXT";
    private static final String COMMA_SEP  = ",";

    /**
     * CatalogSubgroup
     */
    public static final String SQL_CREATE_CATALOG_SUBGROUPS =
            "CREATE TABLE " + CatalogContract.CatalogSubgroup.TABLE_NAME + " ( " +
                    CatalogContract.CatalogSubgroup._ID + " INTEGER PRIMARY KEY, "+
                    CatalogContract.CatalogSubgroup.COLUMN_NAME_LINE_NAME      + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroup.COLUMN_NAME_GROUP_NAME     + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGROUP_NAME  + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGROUP_PRICE + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGROUP_PHOTO + TEXT_TYPE +
                    " ) ";

    public static final String SQL_DROP_TABLE_CATALOG_SUBGROUPS =
            "DROP TABLE IF EXISTS " + CatalogContract.CatalogSubgroup.TABLE_NAME;

    /**
     * CatalogSubgroupFlavors
     */
    public static final String SQL_CREATE_CATALOG_SUBGROUP_FLAVORS =
            "CREATE TABLE " + CatalogContract.CatalogSubgroupFlavors.TABLE_NAME + " ( " +
                    CatalogContract.CatalogSubgroupFlavors._ID + "INTEGER PRIMARY KEY, "+
                    CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGROUP_NAME    + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGROUP_FLAVORS + TEXT_TYPE +
                    " ) ";

    public static final String SQL_DROP_TABLE_CATALOG_SUBGROUP_FLAVORS =
            "DROP TABLE IF EXISTS " + CatalogContract.CatalogSubgroupFlavors.TABLE_NAME;

    /**
     * CatalogSubgroupPhotos
     */
    public static final String SQL_CREATE_CATALOG_SUBGROUP_PHOTOS =
            "CREATE TABLE " + CatalogContract.CatalogSubgroupPhotos.TABLE_NAME + " ( " +
                    CatalogContract.CatalogSubgroupPhotos._ID + "INTEGER PRIMARY KEY, "+
                    CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_LINE           + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_SUBGROUP_NAME  + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_PHOTOS_PATH    + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_NUM_PHOTOS     + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_PHOTOS_NAME    + TEXT_TYPE +
                    " ) ";

    public static final String SQL_DROP_TABLE_CATALOG_SUBGROUP_PHOTOS =
            "DROP TABLE IF EXISTS " + CatalogContract.CatalogSubgroupPhotos.TABLE_NAME;

    /**
     * Events
     */
    public static final String SQL_CREATE_EVENTS =
            "CREATE TABLE " + CatalogContract.Events.TABLE_NAME + " ( " +
                    CatalogContract.Events._ID + "INTEGER PRIMARY KEY, "+
                    CatalogContract.Events.COLUMN_NAME_EVENT_CODE        + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.Events.COLUMN_NAME_EVENT_NAME        + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.Events.COLUMN_NAME_EVENT_TYPE        + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.Events.COLUMN_NAME_EVENT_MAIN_PHOTO  + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.Events.COLUMN_NAME_EVENT_DESCRIPTION + TEXT_TYPE +
                    " ) ";

    public static final String SQL_DROP_TABLE_EVENTS =
            "DROP TABLE IF EXISTS " + CatalogContract.Events.TABLE_NAME;


    public static final String SQL_CREATE_EVENTS_PHOTOS =
            "CREATE TABLE " + CatalogContract.EventsPhotos.TABLE_NAME + " ( " +
                    CatalogContract.EventsPhotos._ID + "INTEGER PRIMARY KEY, "+
                    CatalogContract.EventsPhotos.COLUMN_NAME_EVENT_CODE       + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.EventsPhotos.COLUMN_NAME_PHOTOS_PATH      + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.EventsPhotos.COLUMN_NAME_NUM_PHOTOS       + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.EventsPhotos.COLUMN_NAME_PHOTOS_NAME      + TEXT_TYPE +
                    " ) ";

    public static final String SQL_DROP_TABLE_EVENTS_PHOTOS =
            "DROP TABLE IF EXISTS " + CatalogContract.EventsPhotos.TABLE_NAME;

}
