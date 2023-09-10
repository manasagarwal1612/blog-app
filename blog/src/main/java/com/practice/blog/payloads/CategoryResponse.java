package com.practice.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CategoryResponse {

	private List<CategoryDto> catogories;
	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private long totalElements;
	private boolean lastpage;
	
}
