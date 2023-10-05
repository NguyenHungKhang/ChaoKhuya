package com.chaokhuya.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageResponse<T> {
	private List<T> content;
	private Integer page;
	private Integer size;
	private Long totalElemnts;
	private Integer totalPages;
	private Boolean last;
}
