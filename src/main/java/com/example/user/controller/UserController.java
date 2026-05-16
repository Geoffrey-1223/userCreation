package com.example.user.controller;
import com.example.user.services.ExternalApiServices;
import com.example.user.entity.User;
import com.example.user.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController{
	private final UserService userService;
	private final ExternalApiServices externalApiService;

	public UserController(UserService userService, ExternalApiServices externalApiService) {
		this.userService = userService;
		this.externalApiService = externalApiService;
	}

	// CREATE USER
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	// GET ALL USERS
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	// GET USER BY ID
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	// UPDATE USER
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id,
						   @RequestBody User user) {
		return userService.updateUser(id, user);
	}

	// DELETE USER
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		Optional<User> user = userService.getUserById(id);
		if (user.isPresent()) {
			userService.deleteUser(id);
			return "User deleted successfully";
		}
		return "User not found";
	}

	@GetMapping("/pagination")
	public Page<User> getUsersWithPagination(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		return userService.getUsersWithPagination(page, size);
	}

	@GetMapping("/external/users")
	public String getExternalUsers() {
		return externalApiService.getExternalUsers();
	}
}