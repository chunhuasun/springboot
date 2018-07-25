package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.LibraryIdeaInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface LibraryIdeaInfoRepository extends JpaRepository<LibraryIdeaInfo,Integer> {
	
	@Query(value = "select * from library_idea_info where problem = :problem order by rand() limit :limitCount ", nativeQuery = true)
	List<LibraryIdeaInfo> findByProblem(@Param("problem") String problem,@Param("limitCount") Integer limitCount);
	 
	@Query(value = "select * from library_idea_info where id = :id ", nativeQuery = true)
	List<LibraryIdeaInfo> findById(@Param("id") Integer id);
	
	@Query(value = "select * from library_idea_info order by rand() limit :limitCount ", nativeQuery = true)
	List<LibraryIdeaInfo> findByRand(@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from library_idea_info where problem = :problem and think_answer = :thinkAnswer limit :limitCount ", nativeQuery = true)
	List<LibraryIdeaInfo> findByProblemAndAnswer(@Param("problem") String problem,@Param("thinkAnswer") String thinkAnswer,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from library_idea_info where problem = :problem order by ifnull(think_rank,1) desc limit :limitCount ", nativeQuery = true)
	List<LibraryIdeaInfo> findByProblemOrder(@Param("problem") String problem,@Param("limitCount") Integer limitCount);
}
