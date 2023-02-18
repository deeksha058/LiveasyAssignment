package com.LiveasyAssignment.LiveasyAssignment.Service;

import com.LiveasyAssignment.LiveasyAssignment.Model.Loads;
import com.LiveasyAssignment.LiveasyAssignment.Repository.LoadsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoadsService {

    @Autowired
    private LoadsRepository loadsRepository;


    public List<Loads> getAllData() {
        List<Loads> loads = loadsRepository.loadsData(1000 ,0);
        return loads;
    }

    public void saveLoadsData(Loads loads) {
        Loads saveLoadData = loadsRepository.save(loads);
    }

    public Loads deleteLoadsData(Integer id) {
        try{
            Loads loadsData = loadsRepository.findById(id).orElseThrow();
            loadsRepository.deleteById(id);
            return loadsData;

        }catch (Exception e){
            System.out.println("LoadsData not found");
        }
        return null;
    }

    public Loads updateLoadsData(Loads loads , Integer id ) {

        try {
            Loads loadDataWithId = loadsRepository.findById(id).orElseThrow();

            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(loads);
            Loads loadsData = mapper.readValue(jsonString, Loads.class);
            loadsData.setId(id);
            Loads saveLoadsData = loadsRepository.save(loadsData);
            return saveLoadsData;

        } catch (Exception e) {
            System.out.println("loadsData not found in database " + e);
        }
        return null;
    }

    public Loads getDataById(Integer id) {
        try {
            Loads loads = loadsRepository.findById(id).orElseThrow();
            return loads;
        }catch (Exception e) {
            System.out.println("Loads not found");
            return null;
        }
    }

    public List<Loads> loadsByShipperId(UUID id){
        List<Loads> loads = loadsRepository.loadsWithTheShipperId(id);
        return loads;
    }
}
