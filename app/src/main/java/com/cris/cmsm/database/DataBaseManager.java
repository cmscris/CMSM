package com.cris.cmsm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cris.cmsm.dao.DivisionDao;
import com.cris.cmsm.dao.RailwayDao;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Railway;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class DataBaseManager {
    Context context;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    DaoMaster.DevOpenHelper helper;
    RailwayDao railwayDao;
    DivisionDao divisionDao;
    public SQLiteDatabase database;

    public DataBaseManager(Context context) {
        this.context = context;
    }

    public void createDatabase() {
        try {
            helper = new DaoMaster.DevOpenHelper(context,
                    "CRIS.db", null);
            db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
            railwayDao = daoSession.getRailwayDao();
            divisionDao = daoSession.getDivisionDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /********
     * CRIS Database
     **********/
    public DaoSession getDaoSession() {
        return daoSession;
    }


    public void insertRailway(Railway railway) {
        railwayDao.insert(railway);
    }

    public void insertDivision(Division division) {
        divisionDao.insert(division);
    }


    public List<Railway> getRailwayList(boolean isBoard) {
        QueryBuilder<Railway> qb = railwayDao.queryBuilder();
        List<Railway> list = qb.orderAsc(
                RailwayDao.Properties.Fname)
                .list();
        if (isBoard) {
            Railway railway = new Railway();
            railway.setRlycode("");
            railway.setFlag("");
            railway.setFname("ALL RAILWAY");
            list.add(0, railway);
        }
        return list;
    }

    public List<Railway> getRailwayListByCode(String railwayCode) {
        QueryBuilder<Railway> qb = railwayDao.queryBuilder();
        List<Railway> list = qb.where(DivisionDao.Properties.Rlycode.eq(railwayCode)).orderAsc(
                RailwayDao.Properties.Fname)
                .list();
        return list;
    }


    public List<Division> getDivisionList(String railwayCode) {
        QueryBuilder<Division> qb = divisionDao.queryBuilder();
        List<Division> list = qb.where(DivisionDao.Properties.Rlycode.eq(railwayCode)).orderAsc(
                DivisionDao.Properties.Fname)
                .list();
        Division division = new Division();
        division.setDivid("");
        division.setDivcode("");
        division.setFname("ALL DIVISION");
        list.add(0, division);
        return list;
    }


    public List<Division> getSingleDivisionList(String railwayCode) {
        QueryBuilder<Division> qb = divisionDao.queryBuilder();
        List<Division> list = qb.where(DivisionDao.Properties.Divcode.eq(railwayCode)).orderAsc(
                DivisionDao.Properties.Fname)
                .list();


        return list;
    }




    public List<Division> getDivisionListByDivCode(String divCode) {
        QueryBuilder<Division> qb = divisionDao.queryBuilder();
        List<Division> list = qb.where(DivisionDao.Properties.Divcode.like("%" + divCode + "%")).orderAsc(
                DivisionDao.Properties.Fname)
                .list();
        Log.d("Logging------------ " , "" + list.size());
        return list;
    }


    public void updateRailway(Railway railway) {
        railwayDao.update(railway);
    }

    public void updateDivision(Division division) {
        divisionDao.update(division);
    }

    public void deleteRailway(Railway railway) {
        railwayDao.delete(railway);
    }

    public void deleteRailwayALL() {
        railwayDao.deleteAll();
    }

    public void deleteDivision(Division division) {
        divisionDao.delete(division);
    }

    public void deleteDivisionALL() {
        divisionDao.deleteAll();
    }

}
