package com.tradeshift.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.tradeshift.data.HierarchyRepository;
import com.tradeshift.data.entity.Node;
import com.tradeshift.request.ChangeParentRequest;

@Component
public class HierarchyService {

	@Autowired
	private HierarchyRepository hierarchyRepository;

	public boolean isExist(String nodeId) {
		return hierarchyRepository.existsById(nodeId);
	}

	public Iterable<Node> getAllNodes() {
		return hierarchyRepository.findAll();
	}

	public Node getNodeDetails(String nodeId) {
		return hierarchyRepository.findById(nodeId).get();
	}

	public List<Node> getChildren(String nodeId) {
		return hierarchyRepository.getHierarchy(nodeId);
	}

	public Node createNewRoot() {
		return hierarchyRepository.save(new Node());
	}

	public Node createNewNode(String parentId) {
		Node parentNode = getNodeDetails(parentId);

		Node node = new Node();
		node.setParentId(parentId);
		node.setRootId(StringUtils.isEmpty(parentNode.getRootId()) ? parentId : parentNode.getRootId());
		node.setHeight(parentNode.getHeight() + 1);

		return hierarchyRepository.save(node);
	}

	public Node makeRoot(String nodeId) {
		Node node = getNodeDetails(nodeId);
		node.setParentId(null);
		node.setRootId(null);
		node.setHeight(0);
		return hierarchyRepository.save(node);
	}

	public Node changeParent(ChangeParentRequest request) {
		Node node = getNodeDetails(request.getNodeId());
		Node parentNode = getNodeDetails(request.getNewParentId());

		String newRoot=null;
		if(StringUtils.isEmpty(parentNode.getRootId())) {
			if(StringUtils.isEmpty(parentNode.getParentId())) {
				newRoot=parentNode.getId();
			}else {
				newRoot=parentNode.getParentId();
			}
		}else {
			newRoot=parentNode.getRootId();
		}
		
		node.setParentId(parentNode.getId());
		node.setRootId(newRoot);
		node.setHeight(parentNode.getHeight() + 1);

		return hierarchyRepository.save(node);
	}

}
