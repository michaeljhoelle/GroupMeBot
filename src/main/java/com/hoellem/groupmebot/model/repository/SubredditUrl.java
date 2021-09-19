package com.hoellem.groupmebot.model.repository;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "subreddit_url")
@AllArgsConstructor
@NoArgsConstructor
public class SubredditUrl
{

    @Id
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private LocalDateTime timestamp;

}
