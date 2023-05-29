package com.bjtu.web.spring_boot;

import com.bjtu.web.spring_boot.entity.Staff;
import com.bjtu.web.spring_boot.mapper.StaffMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {




        @Autowired
        private StaffMapper stusMapper;


        @Test
        public void testSelect () {
            List<Staff> Staff = stusMapper.selectList(null);
            for (Staff staff : Staff) {
                System.out.println(Staff);
            }
        }

    }



