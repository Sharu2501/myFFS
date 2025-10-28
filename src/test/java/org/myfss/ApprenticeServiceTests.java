package org.myfss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.myfss.exception.ApprenticeNotFoundException;
import org.myfss.model.Apprentice;
import org.myfss.model.enums.Major;
import org.myfss.repository.ApprenticeRepository;
import org.myfss.service.ApprenticeService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApprenticeServiceTests {

    @Mock
    private ApprenticeRepository apprenticeRepository;

    @InjectMocks
    private ApprenticeService apprenticeService;

    private Apprentice apprentice;

    @BeforeEach
    void setUp() {
        apprentice = Apprentice.builder()
                .id(1L)
                .firstName("Lucas")
                .lastName("Faria")
                .major(Major.I1)
                .build();
    }

    @Test
    void getAllApprentices_shouldReturnOnlyNonAlumni() {
        when(apprenticeRepository.findByMajorNot(Major.ALUMNI))
                .thenReturn(List.of(apprentice));

        List<Apprentice> result = apprenticeService.getAllApprentices();

        assertThat(result).containsExactly(apprentice);
        verify(apprenticeRepository).findByMajorNot(Major.ALUMNI);
    }

    @Test
    void getAllApprenticesAndAlumni_shouldReturnAll() {
        when(apprenticeRepository.findAll()).thenReturn(List.of(apprentice));

        List<Apprentice> result = apprenticeService.getAllApprenticesAndAlumni();

        assertThat(result).containsExactly(apprentice);
        verify(apprenticeRepository).findAll();
    }

    @Test
    void getApprenticeById_shouldReturnApprentice() {
        when(apprenticeRepository.findById(1L)).thenReturn(Optional.of(apprentice));

        Apprentice result = apprenticeService.getApprenticeById(1L);

        assertThat(result).isEqualTo(apprentice);
    }

    @Test
    void getApprenticeById_shouldThrowExceptionWhenNotFound() {
        when(apprenticeRepository.findById(1L)).thenReturn(Optional.empty());

        ApprenticeNotFoundException exception = assertThrows(ApprenticeNotFoundException.class,
                () -> apprenticeService.getApprenticeById(1L));

        assertThat(exception.getMessage()).contains("introuvable");
    }
}
