//package com.maids.maidstask.patrons.services;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import com.maids.maidstask.Utils;
//import com.maids.maidstask.exceptions.NotFoundException;
//import com.maids.maidstask.patrons.Dtos.PatronResponseDto;
//import com.maids.maidstask.patrons.Dtos.UpdatePatronRequestDto;
//import com.maids.maidstask.patrons.models.Patron;
//import com.maids.maidstask.patrons.repositories.PatronRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//@ExtendWith(MockitoExtension.class)
//class PatronsServiceTest {
//
//    @Mock
//    private PatronRepository patronRepository;
//
//    @Mock
//    private PatronMapper patronMapper;
//
//    @Mock
//    private Utils utils;
//
//    @InjectMocks
//    private PatronsService patronsService;
//
//    private Patron testPatron;
//    private PatronResponseDto testPatronResponseDto;
//
//    @BeforeEach
//    void setUp() {
//        testPatron = new Patron();
//        testPatron.setId(1);
//        testPatron.setUsername("test_username");
//
//        testPatronResponseDto = PatronResponseDto.builder()
//                .username("test_username")
//                .email("test@email.com")
//                .address("test_address")
//                .phone_number("test_phone_number")
//                .build();
//
//    }
//
//    @Test
//    void testGetPatronById() throws NotFoundException {
//        // Arrange
//        Integer id = 1;
//        when(patronRepository.findById(id)).thenReturn(Optional.of(testPatron));
//        when(patronMapper.toPatronResponseDto(testPatron)).thenReturn(testPatronResponseDto);
//
//        // Act
//        PatronResponseDto result = patronsService.getPatronById(id);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(testPatronResponseDto, result);
//    }
//
//    @Test
//    void testUpdatePatronById() throws NotFoundException {
//        // Arrange
//        Integer id = 1;
//        UpdatePatronRequestDto newData = UpdatePatronRequestDto.builder().username("updated_username").build();
//
//        when(patronRepository.findById(id)).thenReturn(Optional.of(testPatron));
//        when(utils.assignObjects(testPatron, newData)).then(invocation -> {
//            Patron patron = invocation.getArgument(0);
//            UpdatePatronRequestDto updateData = invocation.getArgument(1);
//            patron.username(updateData.username());
//            return null;
//        });
//        when(patronMapper.toPatronResponseDto(testPatron)).thenReturn(testPatronResponseDto);
//
//        // Act
//        PatronResponseDto result = patronsService.updatePatronById(id, newData);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(testPatronResponseDto, result);
//        assertEquals(newData.getUsername(), testPatron.getUsername());
//    }
//
//    @Test
//    void testDeletePatronById() throws NotFoundException {
//        // Arrange
//        Integer id = 1;
//        when(patronRepository.findById(id)).thenReturn(Optional.of(testPatron));
//
//        // Act
//        CustomResponseMessage result = patronsService.deletePatronById(id);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(HttpStatus.OK, result.getStatus());
//        assertEquals("Patron deleted successfully", result.getMessage());
//    }
//
//    @Test
//    void testGetFullPatronById() throws NotFoundException {
//        // Arrange
//        Integer id = 1;
//        when(patronRepository.findById(id)).thenReturn(Optional.of(testPatron));
//
//        // Act
//        Patron result = patronsService.getFullPatronById(id);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(testPatron, result);
//    }
//}