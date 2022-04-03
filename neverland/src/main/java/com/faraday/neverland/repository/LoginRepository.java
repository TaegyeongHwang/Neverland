package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Destination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class LoginRepository {

    private final EntityManager em;

}
