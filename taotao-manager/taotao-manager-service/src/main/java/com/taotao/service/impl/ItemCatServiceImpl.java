package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品分类管理
 * @author Administrator
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{
	
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		// TODO Auto-generated method stub
		//根据父节点id查询子节点列表
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria=example.createCriteria();
		
		//设置parentid
		criteria.andParentIdEqualTo(parentId);
		
		//执行查询
		List<TbItemCat> list=itemCatMapper.selectByExample(example);
		
		//转化成EasyUITreeNode列表
		List<EasyUITreeNode> resultList=new ArrayList<EasyUITreeNode>();
		for(TbItemCat tbItemCat:list){
			EasyUITreeNode node=new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			//如果节点下有子节点"closed",如果没有"open"
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加到节点列表
			resultList.add(node);
		}
		return resultList;
	}

}
