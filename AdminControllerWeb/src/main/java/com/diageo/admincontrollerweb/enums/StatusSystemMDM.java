/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.enums;

/**
 *
 * @author EDUARDO
 */
public enum StatusSystemMDM {

    PENDING_TMC, PENDING_KAM, PENDING_COMMERCIAL_MANAGER, PENDING_TMC_POTENTIAL, APPROVED, REJECT;

    public static StatusSystemMDM statusEngine(StatusSystemMDM statusIn, Integer idProfile) {
        ProfileEnum profile = ProfileEnum.valueOf(idProfile);
        if (null != profile) switch (profile) {
            case TMC_DISTRIBUIDORES:
                return PENDING_COMMERCIAL_MANAGER;
            case KAM:
                return PENDING_COMMERCIAL_MANAGER;
            case CP_A_DISTRIBUIDORES:
                return PENDING_TMC_POTENTIAL;
            case COMMERCIAL_MANAGER:
                return statusIn;
            case NAM:
                return statusIn;
            default:
                break;
        }
        return statusIn;
    }
}
