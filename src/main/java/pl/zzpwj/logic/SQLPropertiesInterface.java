package pl.zzpwj.logic;

interface SQLPropertiesInterface {
    String dbFilepath = "";
    String db = "jdbc:sqlite" + dbFilepath;
}
