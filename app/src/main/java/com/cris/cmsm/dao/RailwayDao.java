package com.cris.cmsm.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.cris.cmsm.database.DaoSession;
import com.cris.cmsm.models.Railway;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table RAILWAY.
*/
public class RailwayDao extends AbstractDao<Railway, Long> {

    public static final String TABLENAME = "RAILWAY";

    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Rlycode = new Property(1, String.class, "rlycode", false, "RLYCODE");
        public final static Property Sname = new Property(2, String.class, "sname", false, "SNAME");
        public final static Property Fname = new Property(3, String.class, "fname", false, "FNAME");
        public final static Property Flag = new Property(4, String.class, "flag", false, "FLAG");
    }


    public RailwayDao(DaoConfig config) {
        super(config);
    }
    
    public RailwayDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String sql = "CREATE TABLE " + (ifNotExists? "IF NOT EXISTS ": "") + "'RAILWAY' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'RLYCODE' TEXT," + // 1: rlycode
                "'SNAME' TEXT," + // 2: sname
                "'FNAME' TEXT," + // 3: fname
                "'FLAG' TEXT);"; // 4: flag
        db.execSQL(sql);
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'RAILWAY'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Railway entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String rlycode = entity.getRlycode();
        if (rlycode != null) {
            stmt.bindString(2, rlycode);
        }
 
        String sname = entity.getSname();
        if (sname != null) {
            stmt.bindString(3, sname);
        }
 
        String fname = entity.getFname();
        if (fname != null) {
            stmt.bindString(4, fname);
        }
 
        String flag = entity.getFlag();
        if (flag != null) {
            stmt.bindString(5, flag);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Railway readEntity(Cursor cursor, int offset) {
        Railway entity = new Railway( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // rlycode
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // sname
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // fname
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // flag
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Railway entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRlycode(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSname(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFname(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFlag(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected Long updateKeyAfterInsert(Railway entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Railway entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}