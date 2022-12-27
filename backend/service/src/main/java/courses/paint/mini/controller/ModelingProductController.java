package courses.paint.mini.controller;

import courses.paint.mini.dto.product.ModelingProductDto;
import courses.paint.mini.mapper.product.ModelingProductDtoMapper;
import courses.paint.mini.usecase.product.GetAllModelingProductsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/modeling-products")
@RequiredArgsConstructor
public class ModelingProductController {

    private final GetAllModelingProductsUseCase getAllModelingProductsUseCase;
    private final ModelingProductDtoMapper modelingProductMapper;

    @GetMapping
    public List<ModelingProductDto> getAll() {
        return getAllModelingProductsUseCase.execute().stream()
                .map(modelingProductMapper::fromModelingProduct)
                .toList();
    }

}
