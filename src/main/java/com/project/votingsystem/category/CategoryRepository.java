package com.project.votingsystem.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Optionally add custom queries if needed
}
