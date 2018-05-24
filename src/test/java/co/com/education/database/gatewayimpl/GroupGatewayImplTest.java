package co.com.education.database.gatewayimpl;

import co.com.education.config.H2JpaConfig;
import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
public class GroupGatewayImplTest {


    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void ShouldGetAllGroups() throws Exception {
    }

    @Test
    public void getGroupById() throws Exception {
    }

    @Test
    public void saveOrUpdateGroup() throws Exception {
    }

    @Test
    public void deleteGroup() throws Exception {
    }

}