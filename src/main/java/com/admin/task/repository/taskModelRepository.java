package com.admin.task.repository;

import com.admin.task.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface taskModelRepository extends JpaRepository<Task,Long>, JpaSpecificationExecutor<Task> {

    @Query(value = "SELECT * FROM task WHERE task.task_number = :task_number", nativeQuery = true)
    List<Task> findByTask_number(@Param("task_number")String taskNumberID);

    @Query(value = "SELECT * FROM task WHERE task.mob = :phone", nativeQuery = true)
    List<Task>  findByPhone(@Param("phone")String phone);

    @Query(value = "SELECT * FROM task WHERE task.email = :email", nativeQuery = true)
    List<Task>  findByEmail(@Param("email")String email);

    @Query(value = "SELECT * FROM task WHERE task.status != 'Закрыта'", nativeQuery = true)
    List<Task>  findNewTask();

    @Transactional
    @Modifying
    @Query(value = "UPDATE task SET status =:status, hard =:hard,"+ "\r\n"+
                    "category =:category, business =:business,"+ "\r\n"+
                     "code_close = :code_close, solution=:solution,"+ "\r\n"+
                      "remark =:remark"+ "\r\n"+
                    "WHERE task_number = :task_number", nativeQuery = true)
    void   UpdateTask(@Param("task_number")String task_number,@Param("status")String status,
                           @Param("hard")String hard,@Param("category")String category,
                           @Param("business")String business,@Param("code_close")String code_close,
                           @Param("solution")String solution,@Param("remark")String remark);

    @Transactional
    @Modifying
    @Query(value = "UPDATE task SET status = 'Закрыта' WHERE task_number = :task_number",
            nativeQuery = true)
    void   UpdateTaskClose(@Param("task_number")String task_number);


    @Transactional
    @Modifying
    @Query(value = "UPDATE task SET remark = :remark WHERE task_number = :task_number",
            nativeQuery = true)
    void   UpdateRemark(@Param("task_number")String task_number,
                        @Param("remark")String remark);

    @Transactional
    @Modifying
    @Query(value = "UPDATE task SET status =:status, hard =:hard,"+ "\r\n"+
            "category =:category, business =:business,"+ "\r\n"+
            "code_close = :code_close"+ "\r\n"+
            "WHERE task_number = :task_number", nativeQuery = true)
    void   UpdateCloseTask(@Param("task_number")String task_number,@Param("status")String status,
                      @Param("hard")String hard,@Param("category")String category,
                      @Param("business")String business,@Param("code_close")String code_close);


}

