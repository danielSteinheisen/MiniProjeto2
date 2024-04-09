package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.dto.DisciplinaFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.ProfessorEntity;
import br.com.fullstackeducation.miniprojeto2.exception.error.DisciplinaByIdNotFoundException;
import br.com.fullstackeducation.miniprojeto2.exception.error.DisciplinaByNomeNotFoundException;
import br.com.fullstackeducation.miniprojeto2.exception.error.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repository.DisciplinaRepository;
import br.com.fullstackeducation.miniprojeto2.repository.ProfessorRepository;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorService professorService;

    @Override
    public DisciplinaEntity criarDisciplina(DisciplinaEntity disciplinaNova) {
        disciplinaNova.setId(null);

        log.info("Criando disciplina -> Salvar: \n{}\n", JsonUtil.objetoParaJson(disciplinaNova));
        ProfessorEntity professor = professorService.buscarProfessorPorId(disciplinaNova.getProfessorId().getId());
        disciplinaNova.setProfessorId(professor);

        log.info("Criando disciplina -> Salvo com sucesso");
        log.debug("Criando disciplina -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(disciplinaNova));
        return disciplinaRepository.save(disciplinaNova);
    }

    @Override
    public List<DisciplinaEntity> listarDisciplinas(DisciplinaFiltro filtro) {
        List<DisciplinaEntity> disciplinas;

        if (StringUtils.hasText(filtro.getNome())) {
            log.info("Buscando todas as disciplinas -> com nome ({})", filtro.getNome());
            disciplinas = disciplinaRepository.findByNomeContainingIgnoreCase(filtro.getNome());
        } else {
            log.info("Buscando todas as disciplinas");
            disciplinas = disciplinaRepository.findAll();
        }

        log.info("Buscando todas as disciplinas -> {} disciplinas encontradas", disciplinas.size());
        log.debug("Buscando todas as disciplinas -> Registros encontrados:\n{}\n",
                JsonUtil.objetoParaJson(disciplinas));
        return disciplinas;
    }

    public DisciplinaEntity buscarDisciplinaPorId(Long id) {
        log.info("Buscando disciplina por ID: {}", id);
        Optional<DisciplinaEntity> disciplina = disciplinaRepository.findById(id);

        if (disciplina.isEmpty()) {
            log.error("Buscando disciplina por id {} -> NÃO Encontrado", id);
            throw new DisciplinaByIdNotFoundException(id);
        }
        log.info("Buscando disciplina por id ({}) -> Encontrado", id);
        log.debug("Buscando disciplina por id ({}) -> Registro encontrado:\n{}\n", id, JsonUtil.objetoParaJson(disciplina.get()));
        return disciplina.get();
    }

    public DisciplinaEntity buscarDisciplinaPorNome(String nome) {
        log.info("Buscando disciplina por Nome: {}", nome);
        Optional<DisciplinaEntity> opt = disciplinaRepository.findTop1ByNome(nome);

        if (opt.isEmpty()) {
            log.error("Buscando disciplina por nome {} -> NÃO Encontrado", nome);
            throw new DisciplinaByNomeNotFoundException(nome);
        }

        log.info("Buscando disciplina por nome ({}) -> Encontrado", nome);
        log.debug("Buscando disciplina por nome ({}) -> Registro encontrado:\n{}\n", nome,
                JsonUtil.objetoParaJson(opt.get()));
        return opt.get();
    }

    public DisciplinaEntity atualizarDisciplina(Long id, DisciplinaEntity disciplina) {

        if (!disciplinaRepository.existsById(id)) {
            log.info("Alterando disciplina com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(disciplina));
            throw new NotFoundException("Disciplina não encontrada com o ID: " + id);
        }
        disciplina.setId(id);
        log.info("Alterando disciplina -> Salvo com sucesso");
        log.debug("Alterando disciplina -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(disciplina));
        return disciplinaRepository.save(disciplina);
    }

    public void excluirDisciplina(Long id) {
        if (!disciplinaRepository.existsById(id)) {
            log.info("Excluindo disciplina com id ({}) -> Excluindo", id);
            throw new NotFoundException("Disciplina não encontrada com o ID: " + id);
        }
        disciplinaRepository.deleteById(id);
        log.info("Excluindo disciplina com id ({}) -> Excluído com sucesso", id);
    }
}