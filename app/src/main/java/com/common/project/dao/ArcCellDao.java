package com.common.project.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.common.project.entity.ArcCell;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ARC_CELL".
*/
public class ArcCellDao extends AbstractDao<ArcCell, Long> {

    public static final String TABLENAME = "ARC_CELL";

    /**
     * Properties of entity ArcCell.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property SrcId = new Property(1, Long.class, "srcId", false, "SRC_ID");
        public final static Property DesId = new Property(2, Long.class, "desId", false, "DES_ID");
        public final static Property Adj = new Property(3, int.class, "adj", false, "ADJ");
    }


    public ArcCellDao(DaoConfig config) {
        super(config);
    }
    
    public ArcCellDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ARC_CELL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"SRC_ID\" INTEGER," + // 1: srcId
                "\"DES_ID\" INTEGER," + // 2: desId
                "\"ADJ\" INTEGER NOT NULL );"); // 3: adj
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ARC_CELL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ArcCell entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long srcId = entity.getSrcId();
        if (srcId != null) {
            stmt.bindLong(2, srcId);
        }
 
        Long desId = entity.getDesId();
        if (desId != null) {
            stmt.bindLong(3, desId);
        }
        stmt.bindLong(4, entity.getAdj());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ArcCell entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long srcId = entity.getSrcId();
        if (srcId != null) {
            stmt.bindLong(2, srcId);
        }
 
        Long desId = entity.getDesId();
        if (desId != null) {
            stmt.bindLong(3, desId);
        }
        stmt.bindLong(4, entity.getAdj());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ArcCell readEntity(Cursor cursor, int offset) {
        ArcCell entity = new ArcCell( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // srcId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // desId
            cursor.getInt(offset + 3) // adj
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ArcCell entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSrcId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setDesId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setAdj(cursor.getInt(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ArcCell entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ArcCell entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ArcCell entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
