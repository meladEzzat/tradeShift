package com.amazing.company.hierarchy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tradeshift.controller.HierarchyController;
import com.tradeshift.data.entity.Node;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HierarchyControllerTest {
//
//    private HierarchyController hierarchyController;
//
//    @Mock
//    private HierarchyRepository hierarchyRepository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        hierarchyController = new HierarchyController(hierarchyRepository);
//    }
//
//    @Test
//    public void shouldNotBeAbleToGetChildrenForANodeThatDoesNotExists() {
//        ResponseEntity<List<Node>> response = hierarchyController.getChildren(5L);
//
//        assertEquals(ResponseEntity.notFound().build(), response,
//                "Should receive a not found response entity");
//    }
//
//    @Test
//    public void shouldNotBeAbleToPutANodeThatDoesNotExists() {
//        Node node = new Node();
//        node.setId(5L);
//        node.setParentId(1L);
//
//        ResponseEntity<String> response = hierarchyController.putNode(node);
//
//        assertEquals(ResponseEntity.notFound().build(), response,
//                "Should receive a not found response entity");
//    }
//
//    @Test
//    public void shouldRejectMakingNodeItsOwnParent() {
//        Node node = new Node();
//        node.setId(1L);
//        node.setParentId(1L);
//        ResponseEntity<String> response = hierarchyController.putNode(node);
//
//        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().body("Parent ID of a node can not be its own ID.");
//
//        assertEquals(expectedResponse, response, "Should receive a bad request response entity");
//    }
}
