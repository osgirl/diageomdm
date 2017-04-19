/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageonegocio.jdbc;

import com.diageo.diageonegocio.beans.PermissionsegmentBeanLocal;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EDUARDO
 */
public class ConecctionJDBC {

    public static Connection conexionSQLServer() {
        try {
            String databaseURL = "jdbc:sqlserver://localhost:1433;databasename=DIAGEO_BUSINESS";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
            System.out.println("Conexion con sqlserver Establecida..");
            return java.sql.DriverManager.getConnection(databaseURL, "sa", "sa");
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(PermissionsegmentBeanLocal.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static void callStoreInsertOutletsUser(Connection con, Integer id) {
        try {
            CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call dbo.SP_DB_PERMISSION_SEGMENTS_INSERT(?) }");
            prcProcedimientoAlmacenado.setInt(1, id);
            prcProcedimientoAlmacenado.execute();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConecctionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void callStoreProcedure(Connection con, DbPermissionSegments entity) {
        try {
            System.out.println("entro metodo call procedimiento");
            CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call dbo.PROCEDURE_DB_PERMISSION_SEGMENTS_INSERT(?,?,?,?,?,?,?,?,?,?,?,?) }");
            prcProcedimientoAlmacenado.setInt(1, entity.getDb3partyId().getDb3partyId());
            prcProcedimientoAlmacenado.setInt(2, entity.getUserId());
            if (entity.getChannelId() != null) {
                prcProcedimientoAlmacenado.setInt(3, entity.getChannelId());
            } else {
                prcProcedimientoAlmacenado.setNull(3, java.sql.Types.INTEGER);
            }
            if (entity.getSubChannelId() != null) {
                prcProcedimientoAlmacenado.setInt(4, entity.getSubChannelId());
            } else {
                prcProcedimientoAlmacenado.setNull(4, java.sql.Types.INTEGER);
            }
            if (entity.getSegmentId() != null) {
                prcProcedimientoAlmacenado.setInt(5, entity.getSegmentId());
            } else {
                prcProcedimientoAlmacenado.setNull(5, java.sql.Types.INTEGER);
            }
            if (entity.getSubSegmentId() != null) {
                prcProcedimientoAlmacenado.setInt(6, entity.getSubSegmentId());
            } else {
                prcProcedimientoAlmacenado.setNull(6, java.sql.Types.INTEGER);
            }
            if (entity.getPotentialId() != null) {
                prcProcedimientoAlmacenado.setInt(7, entity.getPotentialId());
            } else {
                prcProcedimientoAlmacenado.setNull(7, java.sql.Types.INTEGER);
            }
            prcProcedimientoAlmacenado.setString(8, entity.getChannelCheck());
            prcProcedimientoAlmacenado.setString(9, entity.getSubChannelCheck());
            prcProcedimientoAlmacenado.setString(10, entity.getSegmentCheck());
            prcProcedimientoAlmacenado.setString(11, entity.getSubSegmentCheck());
            prcProcedimientoAlmacenado.setString(12, entity.getPotentialCheck());
            System.out.println("call procedimiento antes del execute");
            prcProcedimientoAlmacenado.execute();
            System.out.println("call procedimiento despues del execute");
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PermissionsegmentBeanLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Connection con = conexionSQLServer();
        try {
            DbPermissionSegments ps = new DbPermissionSegments();
            ps.setChannelCheck("0");
            ps.setSubChannelCheck("0");
            ps.setSegmentCheck("0");
            ps.setSubSegmentCheck("0");
            ps.setChannelId(1);
            ps.setSubChannelId(1);
            ps.setSegmentId(1);
            ps.setSubSegmentId(1);
            ps.setPotentialId(1);
            ps.setUserId(14);
            ps.setDb3partyId(new Db3party(1));
            callStoreProcedure(con, ps);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConecctionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
