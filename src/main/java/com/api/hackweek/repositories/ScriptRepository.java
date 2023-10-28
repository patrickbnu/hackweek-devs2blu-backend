package com.api.hackweek.repositories;

import com.api.hackweek.models.script.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScriptRepository extends JpaRepository<Script, UUID> {
}
