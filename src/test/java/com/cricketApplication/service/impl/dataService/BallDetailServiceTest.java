package com.cricketApplication.service.impl.dataService;

import com.cricketApplication.dao.entities.BallDataDao;
import com.cricketApplication.dao.repositories.BallDataRepository;
import com.cricketApplication.service.dataService.BallDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
class BallDetailServiceTest {
    @InjectMocks
    private BallDetailService ballDetailService;
    @Mock
    private BallDataRepository ballDataRepository;
    @Test
    void getAllBallDetails() {
        List<BallDataDao> ballDataDaoList= new ArrayList<>();
        when(ballDataRepository.findByGameId(anyLong())).thenReturn(ballDataDaoList);
        Assertions.assertNotNull(ballDetailService.getAllBallDetails(1l));

    }

    @Test
    void getOverDetails() {
        List<BallDataDao> ballDataDaoList= new ArrayList<>();
        when(ballDataRepository.findByOversBetweenAndGameId(anyFloat(),anyFloat(),anyLong())).thenReturn(ballDataDaoList);
        Assertions.assertNotNull(ballDetailService.getOverDetails(1l,1));
    }

    @Test
    void getOverDetailsByRange() {
        List<BallDataDao> ballDataDaoList= new ArrayList<>();
        when(ballDataRepository.findByOversBetweenAndGameId(anyFloat(),anyFloat(),anyLong())).thenReturn(ballDataDaoList);
        Assertions.assertNotNull(ballDetailService.getOverDetailsByRange(1l,1,1));

    }
}