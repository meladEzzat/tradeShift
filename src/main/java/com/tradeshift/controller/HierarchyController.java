package com.tradeshift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tradeshift.data.entity.Node;
import com.tradeshift.request.ChangeParentRequest;
import com.tradeshift.service.HierarchyService;

@RestController
public class HierarchyController {

	@Autowired
    private HierarchyService hierarchyService;


    @GetMapping("/node")
    public ResponseEntity<Iterable<Node>> getAllNodes() {
        return ResponseEntity.ok(hierarchyService.getAllNodes());
    }
    
    @GetMapping("/node/{nodeId}")
    public ResponseEntity<Node> getNodeDetails(@PathVariable String nodeId) {
        if (!hierarchyService.isExist(nodeId)) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(hierarchyService.getNodeDetails(nodeId));
    }
    
    @GetMapping("/node/{nodeId}/children")
    public ResponseEntity<List<Node>> getChildren(@PathVariable String nodeId) {
        if (!hierarchyService.isExist(nodeId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hierarchyService.getChildren(nodeId));
    }
    
    
    @PostMapping("/node")
    public ResponseEntity<Node> createNewNode(@RequestBody String parentId) {
    	
        if (!hierarchyService.isExist(parentId) || StringUtils.isEmpty(parentId)) {
        	return ResponseEntity.ok(hierarchyService.createNewRoot());
        }
    	
        return ResponseEntity.ok(hierarchyService.createNewNode(parentId));
    }
    

    @PutMapping("/node")
    public ResponseEntity changeParent(@RequestBody ChangeParentRequest request) {
        
    	if (request.getNodeId().equals(request.getNewParentId())) {
            return ResponseEntity
                    .badRequest()
                    .body("Node ID and parent ID cannot be the same.");
        }

        if (!hierarchyService.isExist(request.getNodeId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Node not found");
        }
    	
        if (StringUtils.isEmpty(request.getNewParentId())) {
            return ResponseEntity.ok(hierarchyService.makeRoot(request.getNodeId()));
        }
        
        if (!hierarchyService.isExist(request.getNewParentId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("New parent node not found");
        }
        
        return ResponseEntity.ok(hierarchyService.changeParent(request));
    }
}
