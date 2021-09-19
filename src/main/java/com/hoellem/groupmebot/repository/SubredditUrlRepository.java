package com.hoellem.groupmebot.repository;

import com.hoellem.groupmebot.model.repository.SubredditUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubredditUrlRepository extends JpaRepository<SubredditUrl, String>
{
}
