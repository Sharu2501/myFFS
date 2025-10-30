package org.myfss.service;

import org.myfss.model.Master;
import java.util.List;

public interface MasterService {

    List<Master> getAllMasters();

    Master getMasterById(Long id);

    Master createMaster(Master newMaster);

    Master updateMaster(Long id, Master updatedMaster);
}