/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.jdbc;

import com.diageo.diageomdmweb.bean.dto.reports.DuplicatesDto;
import com.diageo.diageonegocio.beans.PermissionsegmentBean;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.DbPermissionSegments;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EDUARDO
 */
public class ConecctionJDBC {

    public static Connection conexionSQLServer(String ip, String usu, String pass) {
        try {
            String databaseURL = "jdbc:sqlserver://" + ip + ":1433;databasename=DIAGEO_BUSINESS";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
            return java.sql.DriverManager.getConnection(databaseURL, usu, pass);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(PermissionsegmentBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static void callStoreOutletsUser(Connection con, Integer id, String action) {
        try {
            CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call dbo.SP_DB_PERMISSION_SEGMENTS(?,?) }");
            prcProcedimientoAlmacenado.setString(1, action);
            prcProcedimientoAlmacenado.setInt(2, id);
            prcProcedimientoAlmacenado.execute();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConecctionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void callStoreDelete(Connection con, Integer id) {
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
            Logger.getLogger(PermissionsegmentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void callStoreProcedureDBChains(Connection con, int idChain) {
        try {
            CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call dbo.SP_DB_CHAINS(?) }");
            prcProcedimientoAlmacenado.setInt(1, idChain);
            prcProcedimientoAlmacenado.execute();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConecctionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void callStoreProcedureDBOutlets(Connection con, int idChain) {
        try {
            CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call dbo.SP_DB_CHAINS(?) }");
            prcProcedimientoAlmacenado.setInt(1, idChain);
            prcProcedimientoAlmacenado.execute();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConecctionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void callStoreProcedureDBUsers(Connection con, int userId, String distri, String state) {
        try {
            CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call dbo.SP_DB_USERS(?,?,?) }");
            prcProcedimientoAlmacenado.setInt(1, userId);
            prcProcedimientoAlmacenado.setString(2, distri);
            prcProcedimientoAlmacenado.setString(3, state);
            prcProcedimientoAlmacenado.execute();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConecctionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<DuplicatesDto> callStoreProcedureDuplicatesReport(Connection con, String in) {
        List<DuplicatesDto> duplicatesList = new ArrayList<>();
        try {
            CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call dbo.SP_INFORME_DUPLICADOS(?) }");
            prcProcedimientoAlmacenado.setString(1, in);
            ResultSet rs = prcProcedimientoAlmacenado.executeQuery();
            int contador = 0;
            while (rs.next()) {
                DuplicatesDto dto = new DuplicatesDto();
                dto.setOutletId(rs.getInt(1));
                dto.setOutletIdFather(rs.getInt(2));
                dto.setKiernanId(rs.getString(3));
                dto.setNit(rs.getString(4));
                dto.setVerificationNumber(rs.getString(5));
                dto.setCodigoDistribuidor(rs.getString(6));
                dto.setNombreDistribuidor(rs.getString(7));
                dto.setNumberPDV(rs.getString(8));
                dto.setOutletName(rs.getString(9));
                dto.setBusinessName(rs.getString(10));
                dto.setDireccion(rs.getString(11));
                dto.setNombreCiudad(rs.getString(12));
                dto.setNombreDepto(rs.getString(13));
                dto.setCodigoVendedor(rs.getString(14));
                dto.setNombreVendedor(rs.getString(15));
                dto.setPotencial(rs.getString(16));
                dto.setFuncional(rs.getString(17));
                dto.setStatusOutlet(rs.getString(18));
                dto.setStatusMDM(rs.getString(19));
                duplicatesList.add(dto);
            }
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConecctionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return duplicatesList;
    }

    public static void main(String[] args) {
        Connection con = conexionSQLServer("localhost", "sa", "sa");
        try {
            callStoreProcedureDuplicatesReport(con, "NIT");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConecctionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
