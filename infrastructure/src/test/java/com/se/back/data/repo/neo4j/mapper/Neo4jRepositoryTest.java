package com.se.back.data.repo.neo4j.mapper;

import com.se.back.data.repo.neo4j.dataclass.RelationShipDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class Neo4jRepositoryTest {
    @Autowired
    private Neo4jRepository neo4jRepository;

    @Test
    public void test() {
        List<List<RelationShipDTO>> lists = neo4jRepository.searchPath("MATCH (company1:Company{name:\"北京四方继保自动化股份有限公司\"}),(company2:Company{name:\"中国电建集团华东勘测设计研究院有限公司\"}), \n" +
                "    path=(company1)-[*..2]-(company2) \n" +
                "    return path, LENGTH(path) as distance ORDER BY distance ASC limit 10");
        System.out.println("lists = " + lists);
    }

}