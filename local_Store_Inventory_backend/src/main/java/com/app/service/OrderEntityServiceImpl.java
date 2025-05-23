package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.OrderEntityDao;
import com.app.dao.OrderItemsDao;
import com.app.dao.ProductDao;
import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.ComboPlacedOrderDto;
import com.app.dto.OrderItemDto;
import com.app.dto.OrderItemResponseDto;
import com.app.dto.OrderRequestDto;
import com.app.dto.OrderSummaryStaffDto;
import com.app.dto.PlacedOrderDetailsDto;
import com.app.exception.ResourceNotFoundException;
import com.app.pojos.OrderEntity;
import com.app.pojos.OrderItem;
import com.app.pojos.Product;
import com.app.pojos.User;

@Service
@Transactional
public class OrderEntityServiceImpl implements OrderEntityService {

	@Autowired
	private OrderEntityDao orderEntityDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private OrderItemsDao orderItemsDao;
	
	@Autowired
	private ModelMapper mapper;
	
	
	// CUSTOMER related API logic
	@Override
	public ApiResponse placeOrder(OrderRequestDto orderDto) {
		
		User user =  userDao.findById(orderDto.getUserId()).orElseThrow(()->new ResourceNotFoundException(" Invalid user Id "));
		
		OrderEntity orderEntity = new OrderEntity();
		
		orderEntity.setUser(user);
		orderEntity.setOrderDate(LocalDate.now());
		
		double totalAmout = 0.0;
		
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		for(OrderItemDto orderItemDto : orderDto.getOrderItemlist()) {
			
			OrderItem orderItem = new OrderItem();
			
			Product product = productDao.findById(orderItemDto.getProductId()).orElseThrow(()-> new ResourceNotFoundException(" Product id is invalid"));
			
			
			orderItem.setProduct(product);
			
			
			orderItem.setOrder(orderEntity);
			orderItem.setQuantity(orderItemDto.getQuantity());
			orderItem.setUnitPrice(product.getPrize());
			
			
			totalAmout += (product.getPrize() * orderItemDto.getQuantity());
			orderItemList.add(orderItem);
			
		}
		
		orderEntity.setItems(orderItemList);
		orderEntity.setTotalAmount(totalAmout);
		
		orderEntityDao.save(orderEntity);

		
		return  new ApiResponse(" Order Placed Successfully ", true);
	}

	
	//CUSTOMER related API logic
	@Override
	public List<ComboPlacedOrderDto> getPlacedOrderDetailsByUserId(Long userId) {
		
		List<OrderEntity> orderEntities = orderEntityDao.findAllByUserId(userId);
		
		List<ComboPlacedOrderDto> comboPlacedOrderList = new ArrayList<ComboPlacedOrderDto>();
		
		for( OrderEntity items : orderEntities) {
			
				ComboPlacedOrderDto comboDto = mapper.map(items, ComboPlacedOrderDto.class);
			
				List<OrderItem> orderItems =  orderItemsDao.findByOrderId(items.getId());
		
				List<PlacedOrderDetailsDto> placedOrderList = new ArrayList<PlacedOrderDetailsDto>();
		
						for( OrderItem allOrderItems : orderItems) {
			
								PlacedOrderDetailsDto placedOrders = (mapper.map(allOrderItems, PlacedOrderDetailsDto.class));
								placedOrders.setName(allOrderItems.getProduct().getName());
								placedOrderList.add(placedOrders);
		}
						comboDto.setPlacedOrderDetailsDto(placedOrderList);
						
						comboPlacedOrderList.add(comboDto);
								
		}
		
		 return comboPlacedOrderList;
	}


	//Staff related API logic
	//without using Model mapper set properties manually 
	@Override
	public List<OrderSummaryStaffDto> getAllOrder() {
		
		List<OrderEntity> orderEntityList = orderEntityDao.findAll();
		
		List<OrderSummaryStaffDto> orderSummaryStafDto = new ArrayList<OrderSummaryStaffDto>();
		
		
		for(OrderEntity orderEntity : orderEntityList) {
		
			
			OrderSummaryStaffDto singleOrderSummary = new OrderSummaryStaffDto();
			
			singleOrderSummary.setId(orderEntity.getId());
			singleOrderSummary.setOrderDate(orderEntity.getOrderDate());
			singleOrderSummary.setTotalAmount(orderEntity.getTotalAmount());
			singleOrderSummary.setName(orderEntity.getUser().getFirstName() + " "+ orderEntity.getUser().getLastName());
			singleOrderSummary.setEmail(orderEntity.getUser().getEmail());
			singleOrderSummary.setMobNo(orderEntity.getUser().getMobNo());
			
			List<OrderItemResponseDto> orderItemResponseDto = new ArrayList<OrderItemResponseDto>();
			 List<OrderItem> orderItemsList = orderItemsDao.findByOrderId(orderEntity.getId());
			 			 
			 for( OrderItem singleOrderItem : orderItemsList) {
				 
				 OrderItemResponseDto orderItemsDto = new OrderItemResponseDto();
				 				
				 orderItemsDto.setName(singleOrderItem.getProduct().getName());
				 orderItemsDto.setUnitPrice(singleOrderItem.getUnitPrice());
				 orderItemsDto.setQuantity(singleOrderItem.getQuantity());
				 
				 orderItemResponseDto.add(orderItemsDto);
				 				 
			 }
			 
//			 System.out.println(" Size asddasd"+ orderItemResponseDto.size());

			 
			 singleOrderSummary.setOrderItemResponseDto(orderItemResponseDto);
			 
			 orderSummaryStafDto.add(singleOrderSummary);
		}
		
		
		return orderSummaryStafDto;
	}


	////Staff related API logic
	//set properties with ModelMapper by avoid manually
	@Override
	public OrderSummaryStaffDto getOrderDetailsWithOrderId(Long orderId) {
		
		OrderEntity orderEntity = orderEntityDao.findById(orderId).orElseThrow(()-> new ResourceNotFoundException(" Order Id is invalid "));
		
		OrderSummaryStaffDto orderSummaryDto = mapper.map(orderEntity, OrderSummaryStaffDto.class);
		
		User userDetails = userDao.findById(orderEntity.getUser().getId()).orElse(null);//it is null because it surely get userId
		
		mapper.map(userDetails, orderSummaryDto);
		orderSummaryDto.setName(userDetails.getFirstName()+" "+ userDetails.getLastName());
		
		List<OrderItem> orderItemsList= orderItemsDao.findAllByOrderId(orderId);
		
		List<OrderItemResponseDto> NewOrderItemList = new ArrayList<>();

		for(OrderItem singleOrderItem : orderItemsList) {
			
			
			
			OrderItemResponseDto orderResponse = mapper.map(singleOrderItem, OrderItemResponseDto.class);
			orderResponse.setName(singleOrderItem.getProduct().getName());
			NewOrderItemList.add(orderResponse);
			
		}
		
		orderSummaryDto.setOrderItemResponseDto(NewOrderItemList);
		
		return orderSummaryDto;
	}
	

	
	
	
	
	
	
	

}
