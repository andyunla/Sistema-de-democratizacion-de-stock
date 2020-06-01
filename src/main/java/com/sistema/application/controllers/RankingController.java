package com.sistema.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.ProductoRankingDto;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.UtilHelper;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.LocalModel;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.IProductoService;
@Controller
@RequestMapping("ranking")
public class RankingController {
	@Autowired
    @Qualifier("userConverter")
    private UserConverter userConverter;
	@Autowired
    @Qualifier("userRepository")
    private IUserRepository userRepository;
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	// Para que el model pueda ejecutar los services debe ser usado como una instancia de componente
	@Autowired
	private LocalModel localModel;
	
	@GetMapping("")
	public ModelAndView productoRanking() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.RANKIG_ROOT);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDto userDto = userConverter.entityToDto(userRepository.findByUsername(user.getUsername()));
		boolean isGerente = user.getAuthorities().contains(new SimpleGrantedAuthority(UtilHelper.ROLE_GERENTE));
		userDto.setTipoGerente(isGerente);
		modelAndView.addObject("currentUser", userDto);
		List<ProductoRankingDto> productoRanking = localModel.ranking();
		modelAndView.addObject("productoRanking", productoRanking);
		return modelAndView;
	}
}