package com.sparta.logisitcs.company.repository;

import com.sparta.logisitcs.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> , CompanyRepositoryCustom{
}
