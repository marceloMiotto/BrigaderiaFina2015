package br.com.brigaderiafina.brigaderiafina.data;

public class CatalogSQLHelper {

    private static final String TEXT_TYPE  = " TEXT";
    private static final String COMMA_SEP  = ",";
    public  static final int    TABLES_QTY = 3;

    /*
    ** ModuleVersion
     */
    public static final String SQL_CREATE_MODULE_VERSIONS =
            "CREATE TABLE " + CatalogContract.ModuleVersion.TABLE_NAME + " ( " +
                    CatalogContract.ModuleVersion._ID + "INTEGER PRIMARY KEY, "+
                    CatalogContract.ModuleVersion.COLUMN_NAME_MODULE_NAME    + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.ModuleVersion.COLUMN_NAME_MODULE_VERSION + TEXT_TYPE +
                    " ) ";

    public static final String SQL_DROP_TABLE_MODULE_VERSIONS =
            "DROP TABLE IF EXISTS " + CatalogContract.ModuleVersion.TABLE_NAME;

    /**
     * CatalogSubgroup
     */
    public static final String SQL_CREATE_CATALOG_SUBGROUPS =
            "CREATE TABLE " + CatalogContract.CatalogSubgroup.TABLE_NAME + " ( " +
                    CatalogContract.CatalogSubgroup._ID + " INTEGER PRIMARY KEY, "+
                    CatalogContract.CatalogSubgroup.COLUMN_NAME_LINE_NAME      + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME  + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PRICE + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PHOTO + TEXT_TYPE +
                    " ) ";

    public static final String SQL_DROP_TABLE_CATALOG_SUBGROUPS =
            "DROP TABLE IF EXISTS " + CatalogContract.CatalogSubgroup.TABLE_NAME;

    /**
     * CatalogSubgroupFlavors
     */
    public static final String SQL_CREATE_CATALOG_SUBGROUP_FLAVORS =
            "CREATE TABLE " + CatalogContract.CatalogSubgroupFlavors.TABLE_NAME + " ( " +
                    CatalogContract.CatalogSubgroupFlavors._ID + "INTEGER PRIMARY KEY, "+
                    CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_NAME     + TEXT_TYPE + COMMA_SEP +
                    CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_FLAVORS  + TEXT_TYPE +
                    " ) ";

    public static final String SQL_DROP_TABLE_CATALOG_SUBGROUP_FLAVORS =
            "DROP TABLE IF EXISTS " + CatalogContract.CatalogSubgroupFlavors.TABLE_NAME;


}
