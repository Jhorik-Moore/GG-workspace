package com.ggworkspace.admin.application.repository;

import com.ggworkspace.admin.domain.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskModelRepository
        extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    String FIND_BY_TASK_NUMBER = "SELECT * FROM public.task WHERE task.task_number = :task_number";

    String FIND_BY_PHONE = "SELECT * FROM public.task WHERE task.mob = :phone";

    String FIND_BY_EMAIL = "SELECT * FROM public.task WHERE task.email = :email";

    String FIND_BY_PRODUCT = "SELECT * FROM public.task WHERE task.product = :product";

    String FIND_TASKS = "SELECT * FROM public.task WHERE task.status != 'Закрыта'";
    String UPDATE_TASK = """
            UPDATE public.task SET status =:status, hard =:hard, category =:category,
                        business =:business,code_close = :code_close,
                        solution=:solution, remark =:remark
             WHERE task_number = :task_number""";

    String UPDATE_CLOSED_TASK = """
            UPDATE public.task SET status =:status, hard =:hard,
                        category =:category, business =:business,
                        code_close = :code_close
             WHERE task_number = :task_number""";

    String UPDATE_CLOSE_TASK = "UPDATE public.task SET status = 'Закрыта' WHERE task_number = :task_number";

    String UPDATE_REMARK = "UPDATE public.task SET remark = :remark WHERE task_number = :task_number";

    String DELETE_BY_PHONE = "DELETE FROM public.task WHERE task.mob = :phone";


    @Query(value = FIND_BY_TASK_NUMBER, nativeQuery = true)
    List<Task> findByTask_number(@Param("task_number") String taskNumberID);

    @Query(value = FIND_BY_PHONE, nativeQuery = true)
    List<Task> findByPhone(@Param("phone") String phone);

    @Query(value = FIND_BY_EMAIL, nativeQuery = true)
    List<Task> findByEmail(@Param("email") String email);

    @Query(value = FIND_TASKS, nativeQuery = true)
    List<Task> findTasks();

    @Query(value = FIND_BY_PRODUCT, nativeQuery = true)
    List<Task> findByProduct(@Param("product") String product);

    @Transactional
    @Modifying
    @Query(value = UPDATE_TASK, nativeQuery = true)
    void UpdateTask(@Param("task_number") String task_number, @Param("status") String status,
                    @Param("hard") String hard, @Param("category") String category,
                    @Param("business") String business, @Param("code_close") String code_close,
                    @Param("solution") String solution, @Param("remark") String remark);

    @Transactional
    @Modifying
    @Query(value = UPDATE_CLOSE_TASK, nativeQuery = true)
    void UpdateTaskClose(@Param("task_number") String task_number);


    @Transactional
    @Modifying
    @Query(value = UPDATE_REMARK, nativeQuery = true)
    void UpdateRemark(@Param("task_number") String task_number,
                      @Param("remark") String remark);

    @Transactional
    @Modifying
    @Query(value = UPDATE_CLOSED_TASK, nativeQuery = true)
    void UpdateCloseTask(@Param("task_number") String task_number, @Param("status") String status,
                         @Param("hard") String hard, @Param("category") String category,
                         @Param("business") String business, @Param("code_close") String code_close);

    @Transactional
    @Modifying
    @Query(value = DELETE_BY_PHONE, nativeQuery = true)
    void deleteByPhone(@Param("phone") String phone);

}

