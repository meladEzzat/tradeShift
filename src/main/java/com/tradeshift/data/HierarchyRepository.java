package com.tradeshift.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tradeshift.data.entity.Node;

@Repository
public interface HierarchyRepository extends CrudRepository<Node, String> {

    @Query(
            value = "WITH RECURSIVE tree (id) AS (" + 
            		"    SELECT id,parent_id,root_id,height  FROM node WHERE id=:nodeId" + 
            		"    UNION ALL" + 
            		"    SELECT tt.id,tt.parent_id,tt.root_id,tt.height  FROM node tt JOIN tree tr ON tr.id = tt.parent_id" + 
            		")" + 
            		"SELECT * FROM tree",
            nativeQuery = true
    )
    List<Node> getHierarchy(@Param("nodeId") String nodeId);

    
    
	
}
