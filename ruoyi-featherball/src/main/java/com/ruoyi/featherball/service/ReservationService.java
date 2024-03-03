package com.ruoyi.featherball.service;

import com.ruoyi.featherball.domain.Reservation;

import java.util.List;

/**
 * 场地预约service接口
 *
 * @Author fanjaixing
 * @Date 2024/3/3 20:54
 */
public interface ReservationService {

    /**
     * 获取所有预约信息
     * @return 所有预约信息列表
     */
    List<Reservation> getAllReservations();

    /**
     * 根据预约ID获取预约信息
     * @param reservationId 预约ID
     * @return 预约信息
     */
    Reservation getReservationById(Long reservationId);

    /**
     * 添加预约信息
     * @param reservation 待添加的预约信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addReservation(Reservation reservation);

    /**
     * 更新预约信息
     * @param reservation 待更新的预约信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateReservation(Reservation reservation);

    /**
     * 删除预约信息
     * @param reservationId 待删除的预约ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteReservation(Long reservationId);
}