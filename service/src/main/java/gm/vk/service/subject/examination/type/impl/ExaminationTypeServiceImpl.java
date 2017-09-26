package gm.vk.service.subject.examination.type.impl;

import gm.vk.core.converter.subject.examination.type.ExaminationTypeConverter;
import gm.vk.core.converter.subject.examination.type.ExaminationTypeDtoConverter;
import gm.vk.core.dao.subject.examination.type.ExaminationTypeDao;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import gm.vk.exceptions.subject.examination.type.ExaminationTypeNotFoundException;
import gm.vk.service.subject.examination.type.ExaminationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("examinationTypeService")
public class ExaminationTypeServiceImpl implements ExaminationTypeService {

    @Autowired
    private ExaminationTypeDao examinationTypeDao;

    @Autowired
    private ExaminationTypeConverter examinationTypeConverter;

    @Autowired
    private ExaminationTypeDtoConverter examinationTypeDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<ExaminationTypeDto> findAll() {
        return examinationTypeDao.findAll().stream().filter(Objects::nonNull).map(examinationTypeConverter).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public ExaminationTypeDto findOne(@NotNull final Integer id) {

        final Optional<ExaminationType> examinationType = Optional.ofNullable(examinationTypeDao.findOne(id));

        if (examinationType.isPresent()) {
            return examinationTypeConverter.apply(examinationType.get());
        } else {
            throw new ExaminationTypeNotFoundException("ExaminationType was not found");
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public ExaminationTypeDto save(@NotNull final ExaminationTypeDto examination) {
        examinationTypeDao.save(examinationTypeDtoConverter.apply(examination));
        return examination;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final ExaminationTypeDto examination) {
        examinationTypeDao.delete(examinationTypeDtoConverter.apply(examination));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
        examinationTypeDao.delete(id);
    }

}