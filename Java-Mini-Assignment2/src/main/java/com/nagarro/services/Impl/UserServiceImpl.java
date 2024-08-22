package com.nagarro.services.Impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nagarro.entities.User;
import com.nagarro.payloads.PageInfo;
import com.nagarro.payloads.PostResponse;
import com.nagarro.repositories.UserRepository;
import com.nagarro.services.PageableCreator;
import com.nagarro.services.UserService;
import com.nagarro.services.UserSortingService;
/**
 * Implementation of the UserService interface that provides methods for retrieving and sorting user data.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserSortingService sortingService;
   /**
     * Retrieves and sorts a list of users based on the specified parameters.
     *
     * @param offset    The offset for pagination.
     * @param limit     The maximum number of users to retrieve.
     * @param sortType  The type of sorting (e.g., "name" or "age").
     * @param sortOrder The order of sorting (e.g., "even" or "odd").
     * @return A PostResponse containing the sorted user list and pagination information.
     */
	@Override
	public PostResponse getAllUsers(Integer offset, Integer limit, String sortType, String sortOrder) {
		List<User> userList = this.userRepo.findAll();
		List<User> sortedList = this.sortingService.sortUsers(userList, sortType, sortOrder);
		boolean hasPreviousPage = false;
		PageRequest pageRequest = PageRequest.of(0, offset + limit);
		Page<User> pageResult = PageableCreator.createPageFromList(sortedList, pageRequest);
		List<User> pageDataAfterSorting = pageResult.getContent();
		if (pageDataAfterSorting.size() > 0) {
			if (offset > 0) {
				hasPreviousPage = true;
			}
		}
		if (userList.size() > offset)
			pageDataAfterSorting = pageDataAfterSorting.subList(offset, Math.min(offset + limit, userList.size()));
		else
			pageDataAfterSorting = Collections.emptyList();

		PostResponse postResponse = new PostResponse();

		PageInfo pageInfo = new PageInfo();
		pageInfo.setTotal(pageDataAfterSorting.size());
		pageInfo.setHasNextPage(pageResult.hasNext());
		pageInfo.setHasPreviousPage(hasPreviousPage);

		postResponse.setUserList(pageDataAfterSorting);
		postResponse.setPageInfo(pageInfo);
		return postResponse;
	}
}
